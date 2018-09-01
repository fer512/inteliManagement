import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InteliManagementSharedModule } from 'app/shared';
import {
    ProductImComponent,
    ProductImDetailComponent,
    ProductImUpdateComponent,
    ProductImDeletePopupComponent,
    ProductImDeleteDialogComponent,
    productRoute,
    productPopupRoute
} from './';

const ENTITY_STATES = [...productRoute, ...productPopupRoute];

@NgModule({
    imports: [InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProductImComponent,
        ProductImDetailComponent,
        ProductImUpdateComponent,
        ProductImDeleteDialogComponent,
        ProductImDeletePopupComponent
    ],
    entryComponents: [ProductImComponent, ProductImUpdateComponent, ProductImDeleteDialogComponent, ProductImDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementProductImModule {}
