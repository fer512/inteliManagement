import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProductByBooking } from 'app/shared/model/product-by-booking.model';
import { ProductByBookingService } from './product-by-booking.service';
import { ProductByBookingComponent } from './product-by-booking.component';
import { ProductByBookingDetailComponent } from './product-by-booking-detail.component';
import { ProductByBookingUpdateComponent } from './product-by-booking-update.component';
import { ProductByBookingDeletePopupComponent } from './product-by-booking-delete-dialog.component';
import { IProductByBooking } from 'app/shared/model/product-by-booking.model';

@Injectable({ providedIn: 'root' })
export class ProductByBookingResolve implements Resolve<IProductByBooking> {
    constructor(private service: ProductByBookingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((productByBooking: HttpResponse<ProductByBooking>) => productByBooking.body));
        }
        return of(new ProductByBooking());
    }
}

export const productByBookingRoute: Routes = [
    {
        path: 'product-by-booking',
        component: ProductByBookingComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.productByBooking.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-by-booking/:id/view',
        component: ProductByBookingDetailComponent,
        resolve: {
            productByBooking: ProductByBookingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.productByBooking.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-by-booking/new',
        component: ProductByBookingUpdateComponent,
        resolve: {
            productByBooking: ProductByBookingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.productByBooking.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-by-booking/:id/edit',
        component: ProductByBookingUpdateComponent,
        resolve: {
            productByBooking: ProductByBookingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.productByBooking.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productByBookingPopupRoute: Routes = [
    {
        path: 'product-by-booking/:id/delete',
        component: ProductByBookingDeletePopupComponent,
        resolve: {
            productByBooking: ProductByBookingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.productByBooking.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
