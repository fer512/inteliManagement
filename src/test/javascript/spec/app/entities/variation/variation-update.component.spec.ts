/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { VariationUpdateComponent } from 'app/entities/variation/variation-update.component';
import { VariationService } from 'app/entities/variation/variation.service';
import { Variation } from 'app/shared/model/variation.model';

describe('Component Tests', () => {
    describe('Variation Management Update Component', () => {
        let comp: VariationUpdateComponent;
        let fixture: ComponentFixture<VariationUpdateComponent>;
        let service: VariationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [VariationUpdateComponent]
            })
                .overrideTemplate(VariationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VariationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VariationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Variation(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.variation = entity;
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
                    const entity = new Variation();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.variation = entity;
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
