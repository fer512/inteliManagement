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
    listNotifications: ListNotif[] = [
        {
            id: 'abcd-efgh-ijkl-0001',
            code: 'SO-0009',
            type: 'msg_approval_request',
            title: 'Pedido de aprobación',
            comment: 'Requiere tu aprobación para registrar una variacion generada por un Error en la tarifa...',
            status: '-pending',
            icon: 'timer',
            user: 'jperez',
            name: 'Maximiliano Perez',
            date: '2018-08-01 19:20:15',
            approval: true
        },
        {
            id: 'abcd-efgh-ijkl-0002',
            code: 'SO-0015',
            type: 'msg_request_approved',
            title: 'Tú pedido fue Aprobado!',
            comment: 'Requiere tu aprobación para registrar una variacion generada por un Error en la tarifa...',
            status: '-accept',
            icon: 'check_circle_outline',
            user: 'clopez',
            name: 'Raul Alberto Fernandez',
            date: '2018-08-02 15:34:12',
            approval: false
        },
        {
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
        }
    ];

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
            .query({
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
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
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
        }
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
