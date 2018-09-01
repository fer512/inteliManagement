import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotificationIm } from 'app/shared/model/notification-im.model';

@Component({
    selector: 'jhi-notification-im-detail',
    templateUrl: './notification-im-detail.component.html'
})
export class NotificationImDetailComponent implements OnInit {
    notification: INotificationIm;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ notification }) => {
            this.notification = notification;
        });
    }

    previousState() {
        window.history.back();
    }
}
