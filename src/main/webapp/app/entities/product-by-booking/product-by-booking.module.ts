import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    ProductByBookingComponent,
    ProductByBookingDetailComponent,
    ProductByBookingUpdateComponent,
    ProductByBookingDeletePopupComponent,
    ProductByBookingDeleteDialogComponent,
    productByBookingRoute,
    productByBookingPopupRoute
} from './';

const ENTITY_STATES = [...productByBookingRoute, ...productByBookingPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProductByBookingComponent,
        ProductByBookingDetailComponent,
        ProductByBookingUpdateComponent,
        ProductByBookingDeleteDialogComponent,
        ProductByBookingDeletePopupComponent
    ],
    entryComponents: [
        ProductByBookingComponent,
        ProductByBookingUpdateComponent,
        ProductByBookingDeleteDialogComponent,
        ProductByBookingDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementProductByBookingModule {}
