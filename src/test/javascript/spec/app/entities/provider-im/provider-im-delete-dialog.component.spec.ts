/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { ProviderImDeleteDialogComponent } from 'app/entities/provider-im/provider-im-delete-dialog.component';
import { ProviderImService } from 'app/entities/provider-im/provider-im.service';

describe('Component Tests', () => {
    describe('ProviderIm Management Delete Component', () => {
        let comp: ProviderImDeleteDialogComponent;
        let fixture: ComponentFixture<ProviderImDeleteDialogComponent>;
        let service: ProviderImService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProviderImDeleteDialogComponent]
            })
                .overrideTemplate(ProviderImDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProviderImDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProviderImService);
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
