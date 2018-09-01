import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    ApprovalsImComponent,
    ApprovalsImDetailComponent,
    ApprovalsImUpdateComponent,
    ApprovalsImDeletePopupComponent,
    ApprovalsImDeleteDialogComponent,
    approvalsRoute,
    approvalsPopupRoute
} from './';

const ENTITY_STATES = [...approvalsRoute, ...approvalsPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApprovalsImComponent,
        ApprovalsImDetailComponent,
        ApprovalsImUpdateComponent,
        ApprovalsImDeleteDialogComponent,
        ApprovalsImDeletePopupComponent
    ],
    entryComponents: [ApprovalsImComponent, ApprovalsImUpdateComponent, ApprovalsImDeleteDialogComponent, ApprovalsImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementApprovalsImModule {}
