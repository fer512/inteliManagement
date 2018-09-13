import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InteliManagementSharedModule } from 'app/shared';
import { MaterialModule } from '../../shared';
import {
    BookingImComponent,
    BookingImDetailComponent,
    BookingImUpdateComponent,
    BookingImDeletePopupComponent,
    BookingImDeleteDialogComponent,
    bookingRoute,
    bookingPopupRoute
} from './';
import { BookingImMasterComponent } from './booking-im-master.component';

const ENTITY_STATES = [...bookingRoute, ...bookingPopupRoute];

@NgModule({
    imports: [MaterialModule, InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BookingImMasterComponent,
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
