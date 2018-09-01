/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProviderImUpdateComponent } from 'app/entities/provider-im/provider-im-update.component';
import { ProviderImService } from 'app/entities/provider-im/provider-im.service';
import { ProviderIm } from 'app/shared/model/provider-im.model';

describe('Component Tests', () => {
    describe('ProviderIm Management Update Component', () => {
        let comp: ProviderImUpdateComponent;
        let fixture: ComponentFixture<ProviderImUpdateComponent>;
        let service: ProviderImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProviderImUpdateComponent]
            })
                .overrideTemplate(ProviderImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProviderImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProviderImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProviderIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.provider = entity;
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
                    const entity = new ProviderIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.provider = entity;
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
