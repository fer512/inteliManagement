/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { NotificationImDeleteDialogComponent } from 'app/entities/notification-im/notification-im-delete-dialog.component';
import { NotificationImService } from 'app/entities/notification-im/notification-im.service';

describe('Component Tests', () => {
    describe('NotificationIm Management Delete Component', () => {
        let comp: NotificationImDeleteDialogComponent;
        let fixture: ComponentFixture<NotificationImDeleteDialogComponent>;
        let service: NotificationImService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [NotificationImDeleteDialogComponent]
            })
                .overrideTemplate(NotificationImDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NotificationImDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotificationImService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
