import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CompanyIm } from 'app/shared/model/company-im.model';
import { CompanyImService } from './company-im.service';
import { CompanyImComponent } from './company-im.component';
import { CompanyImDetailComponent } from './company-im-detail.component';
import { CompanyImUpdateComponent } from './company-im-update.component';
import { CompanyImDeletePopupComponent } from './company-im-delete-dialog.component';
import { ICompanyIm } from 'app/shared/model/company-im.model';

@Injectable({ providedIn: 'root' })
export class CompanyImResolve implements Resolve<ICompanyIm> {
    constructor(private service: CompanyImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((company: HttpResponse<CompanyIm>) => company.body));
        }
        return of(new CompanyIm());
    }
}

export const companyRoute: Routes = [
    {
        path: 'company-im',
        component: CompanyImComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'inteliManagementApp.company.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'company-im/:id/view',
        component: CompanyImDetailComponent,
        resolve: {
            company: CompanyImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.company.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'company-im/new',
        component: CompanyImUpdateComponent,
        resolve: {
            company: CompanyImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.company.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'company-im/:id/edit',
        component: CompanyImUpdateComponent,
        resolve: {
            company: CompanyImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.company.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const companyPopupRoute: Routes = [
    {
        path: 'company-im/:id/delete',
        component: CompanyImDeletePopupComponent,
        resolve: {
            company: CompanyImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.company.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
