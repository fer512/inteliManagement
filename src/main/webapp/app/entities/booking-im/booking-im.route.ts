import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookingIm } from 'app/shared/model/booking-im.model';
import { BookingImService } from './booking-im.service';
import { BookingImComponent } from './booking-im.component';
import { BookingImDetailComponent } from './booking-im-detail.component';
import { BookingImUpdateComponent } from './booking-im-update.component';
import { BookingImDeletePopupComponent } from './booking-im-delete-dialog.component';
import { IBookingIm } from 'app/shared/model/booking-im.model';
import { BookingImMasterComponent } from './booking-im-master.component';

@Injectable({ providedIn: 'root' })
export class BookingImResolve implements Resolve<IBookingIm> {
    constructor(private service: BookingImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((booking: HttpResponse<BookingIm>) => booking.body));
        }
        return of(new BookingIm());
    }
}

export const bookingRoute: Routes = [
    {
        path: 'booking-im',
        component: BookingImMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'inteliManagementApp.booking.home.title'
        },
        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: BookingImComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    authorities: ['ROLE_USER'],
                    defaultSort: 'id,asc',
                    pageTitle: 'inteliManagementApp.booking.home.title'
                }
            },
            {
                path: ':id/view',
                component: BookingImDetailComponent,
                resolve: {
                    booking: BookingImResolve
                },
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'inteliManagementApp.booking.home.title'
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: ':id/view/:jid',
                component: BookingImDetailComponent,
                resolve: {
                    booking: BookingImResolve
                },
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'inteliManagementApp.booking.home.title'
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'new',
                component: BookingImUpdateComponent,
                resolve: {
                    booking: BookingImResolve
                },
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'inteliManagementApp.booking.home.title'
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: ':id/edit',
                component: BookingImUpdateComponent,
                resolve: {
                    booking: BookingImResolve
                },
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'inteliManagementApp.booking.home.title'
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];

export const bookingPopupRoute: Routes = [
    {
        path: 'booking-im/:id/delete',
        component: BookingImDeletePopupComponent,
        resolve: {
            booking: BookingImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.booking.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
