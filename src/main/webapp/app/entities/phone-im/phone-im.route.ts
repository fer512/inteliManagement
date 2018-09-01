import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PhoneIm } from 'app/shared/model/phone-im.model';
import { PhoneImService } from './phone-im.service';
import { PhoneImComponent } from './phone-im.component';
import { PhoneImDetailComponent } from './phone-im-detail.component';
import { PhoneImUpdateComponent } from './phone-im-update.component';
import { PhoneImDeletePopupComponent } from './phone-im-delete-dialog.component';
import { IPhoneIm } from 'app/shared/model/phone-im.model';

@Injectable({ providedIn: 'root' })
export class PhoneImResolve implements Resolve<IPhoneIm> {
    constructor(private service: PhoneImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((phone: HttpResponse<PhoneIm>) => phone.body));
        }
        return of(new PhoneIm());
    }
}

export const phoneRoute: Routes = [
    {
        path: 'phone-im',
        component: PhoneImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.phone.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'phone-im/:id/view',
        component: PhoneImDetailComponent,
        resolve: {
            phone: PhoneImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.phone.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'phone-im/new',
        component: PhoneImUpdateComponent,
        resolve: {
            phone: PhoneImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.phone.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'phone-im/:id/edit',
        component: PhoneImUpdateComponent,
        resolve: {
            phone: PhoneImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.phone.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const phonePopupRoute: Routes = [
    {
        path: 'phone-im/:id/delete',
        component: PhoneImDeletePopupComponent,
        resolve: {
            phone: PhoneImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.phone.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
