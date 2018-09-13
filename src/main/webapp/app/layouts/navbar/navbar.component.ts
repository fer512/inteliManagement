import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { VERSION } from 'app/app.constants';
import { JhiLanguageHelper, Principal, LoginModalService, LoginService } from 'app/core';
import { ProfileService } from '../profiles/profile.service';
import { Notification } from 'rxjs';
import { NotificationImService } from '../../entities/notification-im/notification-im.service';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { INotificationIm } from 'app/shared/model/notification-im.model';

@Component({
    selector: 'jhi-navbar',
    /*  templateUrl: './navbar.component.html',*/
    templateUrl: './navbar.material.html',
    styleUrls: ['navbar.css']
})
export class NavbarComponent implements OnInit {
    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    countNotification: number = 5;
    notifications: Array<INotificationIm> = new Array<INotificationIm>();

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private notificationService: NotificationImService,
        private router: Router
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;
    }

    ngOnInit() {
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });

        this.profileService.getProfileInfo().then(profileInfo => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });

        this.loadNotification();
    }

    changeLanguage(languageKey: string) {
        this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.loginModalService.open();
    }

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }

    loadNotification() {
        this.notificationService
            .query({
                page: 0,
                size: this.countNotification,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<INotificationIm[]>) => this.paginateNotifications(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    sort() {
        const result = [];
        result.push('id');
        return result;
    }

    private paginateNotifications(data: INotificationIm[], headers: HttpHeaders) {
        let totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.notifications.push(data[i]);
        }
    }

    private onError(errorMessage: string) {
        //this.jhiAlertService.error(errorMessage, null, null);
    }
}
