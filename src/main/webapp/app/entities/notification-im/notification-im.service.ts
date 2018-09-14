import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INotificationIm } from 'app/shared/model/notification-im.model';

type EntityResponseType = HttpResponse<INotificationIm>;
type EntityArrayResponseType = HttpResponse<INotificationIm[]>;

@Injectable({ providedIn: 'root' })
export class NotificationImService {
    private resourceUrl = SERVER_API_URL + 'api/notifications';
    private resourceMyNotifications = SERVER_API_URL + 'api/my-notifications';

    constructor(private http: HttpClient) {}

    create(notification: INotificationIm): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(notification);
        return this.http
            .post<INotificationIm>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(notification: INotificationIm): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(notification);
        return this.http
            .put<INotificationIm>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INotificationIm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INotificationIm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    myNotifications(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .post<INotificationIm[]>(this.resourceMyNotifications, options, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(notification: INotificationIm): INotificationIm {
        const copy: INotificationIm = Object.assign({}, notification, {
            stastDate: notification.stastDate != null && notification.stastDate.isValid() ? notification.stastDate.toJSON() : null,
            endDate: notification.endDate != null && notification.endDate.isValid() ? notification.endDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.stastDate = res.body.stastDate != null ? moment(res.body.stastDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((notification: INotificationIm) => {
            notification.stastDate = notification.stastDate != null ? moment(notification.stastDate) : null;
            notification.endDate = notification.endDate != null ? moment(notification.endDate) : null;
        });
        return res;
    }
}
