import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    ProvinceImComponent,
    ProvinceImDetailComponent,
    ProvinceImUpdateComponent,
    ProvinceImDeletePopupComponent,
    ProvinceImDeleteDialogComponent,
    provinceRoute,
    provincePopupRoute
} from './';

const ENTITY_STATES = [...provinceRoute, ...provincePopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProvinceImComponent,
        ProvinceImDetailComponent,
        ProvinceImUpdateComponent,
        ProvinceImDeleteDialogComponent,
        ProvinceImDeletePopupComponent
    ],
    entryComponents: [ProvinceImComponent, ProvinceImUpdateComponent, ProvinceImDeleteDialogComponent, ProvinceImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementProvinceImModule {}
