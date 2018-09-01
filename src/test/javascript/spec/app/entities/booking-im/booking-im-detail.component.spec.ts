/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { BookingImDetailComponent } from 'app/entities/booking-im/booking-im-detail.component';
import { BookingIm } from 'app/shared/model/booking-im.model';

describe('Component Tests', () => {
    describe('BookingIm Management Detail Component', () => {
        let comp: BookingImDetailComponent;
        let fixture: ComponentFixture<BookingImDetailComponent>;
        const route = ({ data: of({ booking: new BookingIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [BookingImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BookingImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BookingImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.booking).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
