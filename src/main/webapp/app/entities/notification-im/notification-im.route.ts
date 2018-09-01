import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NotificationIm } from 'app/shared/model/notification-im.model';
import { NotificationImService } from './notification-im.service';
import { NotificationImComponent } from './notification-im.component';
import { NotificationImDetailComponent } from './notification-im-detail.component';
import { NotificationImUpdateComponent } from './notification-im-update.component';
import { NotificationImDeletePopupComponent } from './notification-im-delete-dialog.component';
import { INotificationIm } from 'app/shared/model/notification-im.model';

@Injectable({ providedIn: 'root' })
export class NotificationImResolve implements Resolve<INotificationIm> {
    constructor(private service: NotificationImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((notification: HttpResponse<NotificationIm>) => notification.body));
        }
        return of(new NotificationIm());
    }
}

export const notificationRoute: Routes = [
    {
        path: 'notification-im',
        component: NotificationImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.notification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notification-im/:id/view',
        component: NotificationImDetailComponent,
        resolve: {
            notification: NotificationImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.notification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notification-im/new',
        component: NotificationImUpdateComponent,
        resolve: {
            notification: NotificationImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.notification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notification-im/:id/edit',
        component: NotificationImUpdateComponent,
        resolve: {
            notification: NotificationImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.notification.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const notificationPopupRoute: Routes = [
    {
        path: 'notification-im/:id/delete',
        component: NotificationImDeletePopupComponent,
        resolve: {
            notification: NotificationImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.notification.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
