import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Principal } from 'app/core';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { BookingImService } from 'app/entities/booking-im';
import { INotificationIm } from 'app/shared/model/notification-im.model';
import { NotificationImService } from 'app/entities/notification-im';

@Component({
    selector: 'jhi-search',
    /*  templateUrl: './navbar.component.html',*/
    templateUrl: './search.material.html',
    styleUrls: ['search.css']
})
export class SearchComponent implements OnInit {
    currentAccount: any;
    search = null;
    links = [
        { title: 'Mis Registros', routerlink: '/booking-im' },
        { title: 'Mis Pendientes', routerlink: '' },
        { title: 'Mis Pedidos', routerlink: '/variation' },
        { title: 'Notificaciones', routerlink: '/notification-im' }
    ];
    activeLink = this.links[0];
    background = '';
    countNotification: any = 5;
    notifications: INotificationIm[];

    constructor(
        private principal: Principal,
        private bookingService: BookingImService,
        private router: Router,
        private notificationService: NotificationImService
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }

    onSearch() {
        let criteria = { idReserveLocatorJuniper: { equals: this.search } };
        this.bookingService
            .findByCriteria(criteria)
            .subscribe(
                (res: HttpResponse<IBookingIm[]>) => this.onSaveSuccess(res.body),
                (res: HttpErrorResponse) => this.onSaveError(res.message)
            );
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    private onSaveSuccess(list: IBookingIm[]) {
        if (list != null && list.length == 1) {
            this.view(list[0].id);
        } else {
            this.newBooking();
        }
    }

    view(id: number) {
        let url = '/booking-im/' + id + '/view';
        this.router.navigate([url]);
    }

    newBooking() {
        let url = '/booking-im/new';
        this.router.navigate([url]);
    }

    private onSaveError(e: any) {}

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
