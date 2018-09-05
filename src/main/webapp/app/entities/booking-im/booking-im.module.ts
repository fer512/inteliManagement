import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import { MatInputModule } from '@angular/material/input';

import {
    BookingImComponent,
    BookingImDetailComponent,
    BookingImUpdateComponent,
    BookingImDeletePopupComponent,
    BookingImDeleteDialogComponent,
    bookingRoute,
    bookingPopupRoute
} from './';

const ENTITY_STATES = [...bookingRoute, ...bookingPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, MatInputModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BookingImComponent,
        BookingImDetailComponent,
        BookingImUpdateComponent,
        BookingImDeleteDialogComponent,
        BookingImDeletePopupComponent
    ],
    entryComponents: [BookingImComponent, BookingImUpdateComponent, BookingImDeleteDialogComponent, BookingImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementBookingImModule {}
