import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { INotificationIm } from 'app/shared/model/notification-im.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { NotificationImService } from './notification-im.service';

export interface ListNotif {
    id: string;
    code: string;
    type: string;
    title: string;
    comment: string;
    status: string;
    icon: string;
    user: string;
    name: string;
    date: string;
    approval: boolean;
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
        this.reverse = true;
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
            this.notifications.push(data[i]);
            this.listNotifications.push(this.createRow(data[i]));
        }
    }

    createRow(n: INotificationIm): ListNotif {
        return {
            id: 'abcd-efgh-ijkl-0003',
            code: 'SO-0021',
            type: 'msg_request_refused',
            title: 'Tú pedido fue Rechazado',
            comment: 'Requiere tu aprobación para registrar una variacion generada por un Error en la tarifa...',
            status: '-refuse',
            icon: 'highlight_off',
            user: 'mperalta',
            name: 'Martín Peralta',
            date: '2018-08-01 16:59:45',
            approval: false
        };
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
