import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProvinceIm } from 'app/shared/model/province-im.model';
import { ProvinceImService } from './province-im.service';
import { ProvinceImComponent } from './province-im.component';
import { ProvinceImDetailComponent } from './province-im-detail.component';
import { ProvinceImUpdateComponent } from './province-im-update.component';
import { ProvinceImDeletePopupComponent } from './province-im-delete-dialog.component';
import { IProvinceIm } from 'app/shared/model/province-im.model';

@Injectable({ providedIn: 'root' })
export class ProvinceImResolve implements Resolve<IProvinceIm> {
    constructor(private service: ProvinceImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((province: HttpResponse<ProvinceIm>) => province.body));
        }
        return of(new ProvinceIm());
    }
}

export const provinceRoute: Routes = [
    {
        path: 'province-im',
        component: ProvinceImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.province.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'province-im/:id/view',
        component: ProvinceImDetailComponent,
        resolve: {
            province: ProvinceImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.province.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'province-im/new',
        component: ProvinceImUpdateComponent,
        resolve: {
            province: ProvinceImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.province.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'province-im/:id/edit',
        component: ProvinceImUpdateComponent,
        resolve: {
            province: ProvinceImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.province.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const provincePopupRoute: Routes = [
    {
        path: 'province-im/:id/delete',
        component: ProvinceImDeletePopupComponent,
        resolve: {
            province: ProvinceImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.province.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
