/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductByBookingDeleteDialogComponent } from 'app/entities/product-by-booking/product-by-booking-delete-dialog.component';
import { ProductByBookingService } from 'app/entities/product-by-booking/product-by-booking.service';

describe('Component Tests', () => {
    describe('ProductByBooking Management Delete Component', () => {
        let comp: ProductByBookingDeleteDialogComponent;
        let fixture: ComponentFixture<ProductByBookingDeleteDialogComponent>;
        let service: ProductByBookingService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductByBookingDeleteDialogComponent]
            })
                .overrideTemplate(ProductByBookingDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProductByBookingDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductByBookingService);
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
