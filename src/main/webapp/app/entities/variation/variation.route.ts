import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Variation } from 'app/shared/model/variation.model';
import { VariationService } from './variation.service';
import { VariationComponent } from './variation.component';
import { VariationDetailComponent } from './variation-detail.component';
import { VariationUpdateComponent } from './variation-update.component';
import { VariationDeletePopupComponent } from './variation-delete-dialog.component';
import { IVariation } from 'app/shared/model/variation.model';

@Injectable({ providedIn: 'root' })
export class VariationResolve implements Resolve<IVariation> {
    constructor(private service: VariationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((variation: HttpResponse<Variation>) => variation.body));
        }
        return of(new Variation());
    }
}

export const variationRoute: Routes = [
    {
        path: 'variation',
        component: VariationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'inteliManagementApp.variation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variation/:id/view',
        component: VariationDetailComponent,
        resolve: {
            variation: VariationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.variation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variation/new',
        component: VariationUpdateComponent,
        resolve: {
            variation: VariationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.variation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variation/:id/edit',
        component: VariationUpdateComponent,
        resolve: {
            variation: VariationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.variation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const variationPopupRoute: Routes = [
    {
        path: 'variation/:id/delete',
        component: VariationDeletePopupComponent,
        resolve: {
            variation: VariationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.variation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
