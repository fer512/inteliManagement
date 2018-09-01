import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INotificationIm } from 'app/shared/model/notification-im.model';
import { NotificationImService } from './notification-im.service';
import { IEmployeeIm } from 'app/shared/model/employee-im.model';
import { EmployeeImService } from 'app/entities/employee-im';

@Component({
    selector: 'jhi-notification-im-update',
    templateUrl: './notification-im-update.component.html'
})
export class NotificationImUpdateComponent implements OnInit {
    private _notification: INotificationIm;
    isSaving: boolean;

    employees: IEmployeeIm[];
    stastDate: string;
    endDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private notificationService: NotificationImService,
        private employeeService: EmployeeImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ notification }) => {
            this.notification = notification;
        });
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployeeIm[]>) => {
                this.employees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.notification.stastDate = moment(this.stastDate, DATE_TIME_FORMAT);
        this.notification.endDate = moment(this.endDate, DATE_TIME_FORMAT);
        if (this.notification.id !== undefined) {
            this.subscribeToSaveResponse(this.notificationService.update(this.notification));
        } else {
            this.subscribeToSaveResponse(this.notificationService.create(this.notification));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INotificationIm>>) {
        result.subscribe((res: HttpResponse<INotificationIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEmployeeById(index: number, item: IEmployeeIm) {
        return item.id;
    }
    get notification() {
        return this._notification;
    }

    set notification(notification: INotificationIm) {
        this._notification = notification;
        this.stastDate = moment(notification.stastDate).format(DATE_TIME_FORMAT);
        this.endDate = moment(notification.endDate).format(DATE_TIME_FORMAT);
    }
}
