import { NgModule } from '@angular/core';

import { InteliManagementSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent } from './';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileImg } from 'app/shared/util/profile-img.component';

@NgModule({
    imports: [BrowserAnimationsModule, InteliManagementSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, ProfileImg],
    exports: [InteliManagementSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, ProfileImg]
})
export class InteliManagementSharedCommonModule {}
