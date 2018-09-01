import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    AddressImComponent,
    AddressImDetailComponent,
    AddressImUpdateComponent,
    AddressImDeletePopupComponent,
    AddressImDeleteDialogComponent,
    addressRoute,
    addressPopupRoute
} from './';

const ENTITY_STATES = [...addressRoute, ...addressPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AddressImComponent,
        AddressImDetailComponent,
        AddressImUpdateComponent,
        AddressImDeleteDialogComponent,
        AddressImDeletePopupComponent
    ],
    entryComponents: [AddressImComponent, AddressImUpdateComponent, AddressImDeleteDialogComponent, AddressImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementAddressImModule {}
