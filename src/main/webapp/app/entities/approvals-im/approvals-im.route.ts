import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApprovalsIm } from 'app/shared/model/approvals-im.model';
import { ApprovalsImService } from './approvals-im.service';
import { ApprovalsImComponent } from './approvals-im.component';
import { ApprovalsImDetailComponent } from './approvals-im-detail.component';
import { ApprovalsImUpdateComponent } from './approvals-im-update.component';
import { ApprovalsImDeletePopupComponent } from './approvals-im-delete-dialog.component';
import { IApprovalsIm } from 'app/shared/model/approvals-im.model';

@Injectable({ providedIn: 'root' })
export class ApprovalsImResolve implements Resolve<IApprovalsIm> {
    constructor(private service: ApprovalsImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((approvals: HttpResponse<ApprovalsIm>) => approvals.body));
        }
        return of(new ApprovalsIm());
    }
}

export const approvalsRoute: Routes = [
    {
        path: 'approvals-im',
        component: ApprovalsImComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'inteliManagementApp.approvals.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'approvals-im/:id/view',
        component: ApprovalsImDetailComponent,
        resolve: {
            approvals: ApprovalsImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.approvals.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'approvals-im/new',
        component: ApprovalsImUpdateComponent,
        resolve: {
            approvals: ApprovalsImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.approvals.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'approvals-im/:id/edit',
        component: ApprovalsImUpdateComponent,
        resolve: {
            approvals: ApprovalsImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.approvals.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const approvalsPopupRoute: Routes = [
    {
        path: 'approvals-im/:id/delete',
        component: ApprovalsImDeletePopupComponent,
        resolve: {
            approvals: ApprovalsImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.approvals.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
