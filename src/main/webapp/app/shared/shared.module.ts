import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { InteliManagementSharedLibsModule, InteliManagementSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';
import { MaterialModule } from './material.module';

@NgModule({
    imports: [InteliManagementSharedLibsModule, InteliManagementSharedCommonModule, MaterialModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [InteliManagementSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementSharedModule {}
