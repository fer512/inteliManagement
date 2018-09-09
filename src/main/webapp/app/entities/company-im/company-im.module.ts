import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    CompanyImComponent,
    CompanyImDetailComponent,
    CompanyImUpdateComponent,
    CompanyImDeletePopupComponent,
    CompanyImDeleteDialogComponent,
    companyRoute,
    companyPopupRoute
} from './';
import { MaterialModule } from '../../shared';

const ENTITY_STATES = [...companyRoute, ...companyPopupRoute];

@NgModule({
    imports: [MaterialModule, InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CompanyImComponent,
        CompanyImDetailComponent,
        CompanyImUpdateComponent,
        CompanyImDeleteDialogComponent,
        CompanyImDeletePopupComponent
    ],
    entryComponents: [CompanyImComponent, CompanyImUpdateComponent, CompanyImDeleteDialogComponent, CompanyImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementCompanyImModule {}
