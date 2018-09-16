/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductByBookingUpdateComponent } from 'app/entities/product-by-booking/product-by-booking-update.component';
import { ProductByBookingService } from 'app/entities/product-by-booking/product-by-booking.service';
import { ProductByBooking } from 'app/shared/model/product-by-booking.model';

describe('Component Tests', () => {
    describe('ProductByBooking Management Update Component', () => {
        let comp: ProductByBookingUpdateComponent;
        let fixture: ComponentFixture<ProductByBookingUpdateComponent>;
        let service: ProductByBookingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductByBookingUpdateComponent]
            })
                .overrideTemplate(ProductByBookingUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductByBookingUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductByBookingService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductByBooking(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.productByBooking = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductByBooking();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.productByBooking = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
