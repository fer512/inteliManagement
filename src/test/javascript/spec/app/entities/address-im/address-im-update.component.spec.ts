/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { AddressImUpdateComponent } from 'app/entities/address-im/address-im-update.component';
import { AddressImService } from 'app/entities/address-im/address-im.service';
import { AddressIm } from 'app/shared/model/address-im.model';

describe('Component Tests', () => {
    describe('AddressIm Management Update Component', () => {
        let comp: AddressImUpdateComponent;
        let fixture: ComponentFixture<AddressImUpdateComponent>;
        let service: AddressImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [AddressImUpdateComponent]
            })
                .overrideTemplate(AddressImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AddressImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AddressIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.address = entity;
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
                    const entity = new AddressIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.address = entity;
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
