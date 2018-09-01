import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AddressIm } from 'app/shared/model/address-im.model';
import { AddressImService } from './address-im.service';
import { AddressImComponent } from './address-im.component';
import { AddressImDetailComponent } from './address-im-detail.component';
import { AddressImUpdateComponent } from './address-im-update.component';
import { AddressImDeletePopupComponent } from './address-im-delete-dialog.component';
import { IAddressIm } from 'app/shared/model/address-im.model';

@Injectable({ providedIn: 'root' })
export class AddressImResolve implements Resolve<IAddressIm> {
    constructor(private service: AddressImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((address: HttpResponse<AddressIm>) => address.body));
        }
        return of(new AddressIm());
    }
}

export const addressRoute: Routes = [
    {
        path: 'address-im',
        component: AddressImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'address-im/:id/view',
        component: AddressImDetailComponent,
        resolve: {
            address: AddressImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'address-im/new',
        component: AddressImUpdateComponent,
        resolve: {
            address: AddressImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'address-im/:id/edit',
        component: AddressImUpdateComponent,
        resolve: {
            address: AddressImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const addressPopupRoute: Routes = [
    {
        path: 'address-im/:id/delete',
        component: AddressImDeletePopupComponent,
        resolve: {
            address: AddressImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
