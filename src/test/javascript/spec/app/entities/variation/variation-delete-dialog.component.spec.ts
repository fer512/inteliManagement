/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { VariationDeleteDialogComponent } from 'app/entities/variation/variation-delete-dialog.component';
import { VariationService } from 'app/entities/variation/variation.service';

describe('Component Tests', () => {
    describe('Variation Management Delete Component', () => {
        let comp: VariationDeleteDialogComponent;
        let fixture: ComponentFixture<VariationDeleteDialogComponent>;
        let service: VariationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [VariationDeleteDialogComponent]
            })
                .overrideTemplate(VariationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VariationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VariationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
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
                )
            );
        });
    });
});
