import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import { InteliManagementAdminModule } from 'app/admin/admin.module';
import {
    EmployeeImComponent,
    EmployeeImDetailComponent,
    EmployeeImUpdateComponent,
    EmployeeImDeletePopupComponent,
    EmployeeImDeleteDialogComponent,
    employeeRoute,
    employeePopupRoute
} from './';

const ENTITY_STATES = [...employeeRoute, ...employeePopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, InteliManagementAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmployeeImComponent,
        EmployeeImDetailComponent,
        EmployeeImUpdateComponent,
        EmployeeImDeleteDialogComponent,
        EmployeeImDeletePopupComponent
    ],
    entryComponents: [EmployeeImComponent, EmployeeImUpdateComponent, EmployeeImDeleteDialogComponent, EmployeeImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementEmployeeImModule {}
