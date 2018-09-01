/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { BookingImUpdateComponent } from 'app/entities/booking-im/booking-im-update.component';
import { BookingImService } from 'app/entities/booking-im/booking-im.service';
import { BookingIm } from 'app/shared/model/booking-im.model';

describe('Component Tests', () => {
    describe('BookingIm Management Update Component', () => {
        let comp: BookingImUpdateComponent;
        let fixture: ComponentFixture<BookingImUpdateComponent>;
        let service: BookingImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [BookingImUpdateComponent]
            })
                .overrideTemplate(BookingImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BookingImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookingImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BookingIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.booking = entity;
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
                    const entity = new BookingIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.booking = entity;
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
