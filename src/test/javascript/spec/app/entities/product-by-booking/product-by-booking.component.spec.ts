/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductByBookingComponent } from 'app/entities/product-by-booking/product-by-booking.component';
import { ProductByBookingService } from 'app/entities/product-by-booking/product-by-booking.service';
import { ProductByBooking } from 'app/shared/model/product-by-booking.model';

describe('Component Tests', () => {
    describe('ProductByBooking Management Component', () => {
        let comp: ProductByBookingComponent;
        let fixture: ComponentFixture<ProductByBookingComponent>;
        let service: ProductByBookingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductByBookingComponent],
                providers: []
            })
                .overrideTemplate(ProductByBookingComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductByBookingComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductByBookingService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProductByBooking(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.productByBookings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
