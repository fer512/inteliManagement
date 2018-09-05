import { NgModule } from '@angular/core';

import { InteliManagementSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent } from './';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
    imports: [InteliManagementSharedLibsModule, BrowserAnimationsModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent],
    exports: [InteliManagementSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent]
})
export class InteliManagementSharedCommonModule {}
