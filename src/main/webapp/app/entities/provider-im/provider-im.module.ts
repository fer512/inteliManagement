import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    ProviderImComponent,
    ProviderImDetailComponent,
    ProviderImUpdateComponent,
    ProviderImDeletePopupComponent,
    ProviderImDeleteDialogComponent,
    providerRoute,
    providerPopupRoute
} from './';

const ENTITY_STATES = [...providerRoute, ...providerPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProviderImComponent,
        ProviderImDetailComponent,
        ProviderImUpdateComponent,
        ProviderImDeleteDialogComponent,
        ProviderImDeletePopupComponent
    ],
    entryComponents: [ProviderImComponent, ProviderImUpdateComponent, ProviderImDeleteDialogComponent, ProviderImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementProviderImModule {}
