import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { InteliManagementCompanyImModule } from './company-im/company-im.module';
import { InteliManagementAddressImModule } from './address-im/address-im.module';
import { InteliManagementApprovalsImModule } from './approvals-im/approvals-im.module';
import { InteliManagementCountryImModule } from './country-im/country-im.module';
import { InteliManagementProvinceImModule } from './province-im/province-im.module';
import { InteliManagementEmployeeImModule } from './employee-im/employee-im.module';
import { InteliManagementPhoneImModule } from './phone-im/phone-im.module';
import { InteliManagementBookingImModule } from './booking-im/booking-im.module';
import { InteliManagementProviderImModule } from './provider-im/provider-im.module';
import { InteliManagementProductImModule } from './product-im/product-im.module';
import { InteliManagementNotificationImModule } from './notification-im/notification-im.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        InteliManagementCompanyImModule,
        InteliManagementAddressImModule,
        InteliManagementApprovalsImModule,
        InteliManagementCountryImModule,
        InteliManagementProvinceImModule,
        InteliManagementEmployeeImModule,
        InteliManagementPhoneImModule,
        InteliManagementBookingImModule,
        InteliManagementProviderImModule,
        InteliManagementProductImModule,
        InteliManagementNotificationImModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementEntityModule {}
