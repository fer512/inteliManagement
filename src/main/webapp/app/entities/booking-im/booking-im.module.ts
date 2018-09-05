import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { InteliManagementSharedModule } from 'app/shared';
import { MatButtonModule, MatCheckboxModule, MatInputModule } from '@angular/material';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatDividerModule } from '@angular/material/divider';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatChipsModule } from '@angular/material/chips';
import { MatCardModule } from '@angular/material/card';

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
    imports: [
        InteliManagementSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        MatButtonModule,
        MatCheckboxModule,
        MatMenuModule,
        MatInputModule,
        MatSelectModule,
        MatToolbarModule,
        MatIconModule,
        MatGridListModule,
        MatSlideToggleModule,
        MatDividerModule,
        MatAutocompleteModule,
        MatChipsModule,
        MatCardModule
    ],
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
