/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductByBookingDetailComponent } from 'app/entities/product-by-booking/product-by-booking-detail.component';
import { ProductByBooking } from 'app/shared/model/product-by-booking.model';

describe('Component Tests', () => {
    describe('ProductByBooking Management Detail Component', () => {
        let comp: ProductByBookingDetailComponent;
        let fixture: ComponentFixture<ProductByBookingDetailComponent>;
        const route = ({ data: of({ productByBooking: new ProductByBooking(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductByBookingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProductByBookingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProductByBookingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.productByBooking).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
