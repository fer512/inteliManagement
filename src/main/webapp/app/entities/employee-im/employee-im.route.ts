import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmployeeIm } from 'app/shared/model/employee-im.model';
import { EmployeeImService } from './employee-im.service';
import { EmployeeImComponent } from './employee-im.component';
import { EmployeeImDetailComponent } from './employee-im-detail.component';
import { EmployeeImUpdateComponent } from './employee-im-update.component';
import { EmployeeImDeletePopupComponent } from './employee-im-delete-dialog.component';
import { IEmployeeIm } from 'app/shared/model/employee-im.model';

@Injectable({ providedIn: 'root' })
export class EmployeeImResolve implements Resolve<IEmployeeIm> {
    constructor(private service: EmployeeImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((employee: HttpResponse<EmployeeIm>) => employee.body));
        }
        return of(new EmployeeIm());
    }
}

export const employeeRoute: Routes = [
    {
        path: 'employee-im',
        component: EmployeeImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-im/:id/view',
        component: EmployeeImDetailComponent,
        resolve: {
            employee: EmployeeImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-im/new',
        component: EmployeeImUpdateComponent,
        resolve: {
            employee: EmployeeImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-im/:id/edit',
        component: EmployeeImUpdateComponent,
        resolve: {
            employee: EmployeeImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const employeePopupRoute: Routes = [
    {
        path: 'employee-im/:id/delete',
        component: EmployeeImDeletePopupComponent,
        resolve: {
            employee: EmployeeImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.employee.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
