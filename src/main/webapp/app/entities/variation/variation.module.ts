import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../shared';
import { InteliManagementSharedModule } from 'app/shared';
import { InteliManagementAdminModule } from 'app/admin/admin.module';
import {
    VariationComponent,
    VariationDetailComponent,
    VariationUpdateComponent,
    VariationDeletePopupComponent,
    VariationDeleteDialogComponent,
    variationRoute,
    variationPopupRoute
} from './';

const ENTITY_STATES = [...variationRoute, ...variationPopupRoute];

@NgModule({
    imports: [MaterialModule, InteliManagementSharedModule, InteliManagementAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VariationComponent,
        VariationDetailComponent,
        VariationUpdateComponent,
        VariationDeleteDialogComponent,
        VariationDeletePopupComponent
    ],
    entryComponents: [VariationComponent, VariationUpdateComponent, VariationDeleteDialogComponent, VariationDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementVariationModule {}
