import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { INotificationIm } from 'app/shared/model/notification-im.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { NotificationImService } from './notification-im.service';
import { Moment } from 'moment';

export interface ListNotif {
    id: number;
    referenceId: string;
    type: string;
    user: string;
    name: string;
    date: Moment;
}

@Component({
    selector: 'jhi-notification-im',
    templateUrl: './notification-im.component.html',
    styleUrls: ['./notification-im.component.css']
})
export class NotificationImComponent implements OnInit, OnDestroy {
    notifications: INotificationIm[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;
    listNotifications: ListNotif[] = [];

    constructor(
        private notificationService: NotificationImService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private principal: Principal
    ) {
        this.notifications = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = false;
    }

    loadAll() {
        this.notificationService
            .myNotifications({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<INotificationIm[]>) => this.paginateNotifications(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.notifications = [];
        this.listNotifications = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.loadAll();
        });
        this.registerChangeInNotifications();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: INotificationIm) {
        return item.id;
    }

    registerChangeInNotifications() {
        this.eventSubscriber = this.eventManager.subscribe('notificationListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateNotifications(data: INotificationIm[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            const strJson: any = JSON.stringify(data[i].detail);
            this.notifications.push(data[i]);
            this.listNotifications.push(this.createRow(data[i]));
        }
        /* .sort() => b-a (desc) | a-b (asc) */
        /* this.listNotifications.sort((a: any, b: any) => b.referenceId - a.referenceId); */
    }

    createRow(n: INotificationIm): ListNotif {
        return {
            id: n.id,
            referenceId: n.idReference,
            type: n.type,
            user: n.userCreation.login,
            name: n.userCreation.firstName + ' ' + n.userCreation.lastName,
            date: n.creationDate
        };
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
