import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../shared';
import { InteliManagementSharedModule } from 'app/shared';
import {
    NotificationImComponent,
    NotificationImDetailComponent,
    NotificationImUpdateComponent,
    NotificationImDeletePopupComponent,
    NotificationImDeleteDialogComponent,
    notificationRoute,
    notificationPopupRoute
} from './';

const ENTITY_STATES = [...notificationRoute, ...notificationPopupRoute];

@NgModule({
    imports: [MaterialModule, InteliManagementSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NotificationImComponent,
        NotificationImDetailComponent,
        NotificationImUpdateComponent,
        NotificationImDeleteDialogComponent,
        NotificationImDeletePopupComponent
    ],
    entryComponents: [
        NotificationImComponent,
        NotificationImUpdateComponent,
        NotificationImDeleteDialogComponent,
        NotificationImDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InteliManagementNotificationImModule {}
