import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    CountryImComponent,
    CountryImDetailComponent,
    CountryImUpdateComponent,
    CountryImDeletePopupComponent,
    CountryImDeleteDialogComponent,
    countryRoute,
    countryPopupRoute
} from './';

const ENTITY_STATES = [...countryRoute, ...countryPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CountryImComponent,
        CountryImDetailComponent,
        CountryImUpdateComponent,
        CountryImDeleteDialogComponent,
        CountryImDeletePopupComponent
    ],
    entryComponents: [CountryImComponent, CountryImUpdateComponent, CountryImDeleteDialogComponent, CountryImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementCountryImModule {}
