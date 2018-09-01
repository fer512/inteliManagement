/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { PhoneImUpdateComponent } from 'app/entities/phone-im/phone-im-update.component';
import { PhoneImService } from 'app/entities/phone-im/phone-im.service';
import { PhoneIm } from 'app/shared/model/phone-im.model';

describe('Component Tests', () => {
    describe('PhoneIm Management Update Component', () => {
        let comp: PhoneImUpdateComponent;
        let fixture: ComponentFixture<PhoneImUpdateComponent>;
        let service: PhoneImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [PhoneImUpdateComponent]
            })
                .overrideTemplate(PhoneImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PhoneImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhoneImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PhoneIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.phone = entity;
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
                    const entity = new PhoneIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.phone = entity;
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
