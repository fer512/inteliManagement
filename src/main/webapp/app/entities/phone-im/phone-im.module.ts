import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    PhoneImComponent,
    PhoneImDetailComponent,
    PhoneImUpdateComponent,
    PhoneImDeletePopupComponent,
    PhoneImDeleteDialogComponent,
    phoneRoute,
    phonePopupRoute
} from './';

const ENTITY_STATES = [...phoneRoute, ...phonePopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PhoneImComponent,
        PhoneImDetailComponent,
        PhoneImUpdateComponent,
        PhoneImDeleteDialogComponent,
        PhoneImDeletePopupComponent
    ],
    entryComponents: [PhoneImComponent, PhoneImUpdateComponent, PhoneImDeleteDialogComponent, PhoneImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementPhoneImModule {}
