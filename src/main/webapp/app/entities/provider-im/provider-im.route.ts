import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProviderIm } from 'app/shared/model/provider-im.model';
import { ProviderImService } from './provider-im.service';
import { ProviderImComponent } from './provider-im.component';
import { ProviderImDetailComponent } from './provider-im-detail.component';
import { ProviderImUpdateComponent } from './provider-im-update.component';
import { ProviderImDeletePopupComponent } from './provider-im-delete-dialog.component';
import { IProviderIm } from 'app/shared/model/provider-im.model';

@Injectable({ providedIn: 'root' })
export class ProviderImResolve implements Resolve<IProviderIm> {
    constructor(private service: ProviderImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((provider: HttpResponse<ProviderIm>) => provider.body));
        }
        return of(new ProviderIm());
    }
}

export const providerRoute: Routes = [
    {
        path: 'provider-im',
        component: ProviderImComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'inteliManagementApp.provider.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provider-im/:id/view',
        component: ProviderImDetailComponent,
        resolve: {
            provider: ProviderImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.provider.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provider-im/new',
        component: ProviderImUpdateComponent,
        resolve: {
            provider: ProviderImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.provider.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'provider-im/:id/edit',
        component: ProviderImUpdateComponent,
        resolve: {
            provider: ProviderImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.provider.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const providerPopupRoute: Routes = [
    {
        path: 'provider-im/:id/delete',
        component: ProviderImDeletePopupComponent,
        resolve: {
            provider: ProviderImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.provider.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
