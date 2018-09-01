/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { CountryImUpdateComponent } from 'app/entities/country-im/country-im-update.component';
import { CountryImService } from 'app/entities/country-im/country-im.service';
import { CountryIm } from 'app/shared/model/country-im.model';

describe('Component Tests', () => {
    describe('CountryIm Management Update Component', () => {
        let comp: CountryImUpdateComponent;
        let fixture: ComponentFixture<CountryImUpdateComponent>;
        let service: CountryImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [CountryImUpdateComponent]
            })
                .overrideTemplate(CountryImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CountryImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountryImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CountryIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.country = entity;
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
                    const entity = new CountryIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.country = entity;
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
