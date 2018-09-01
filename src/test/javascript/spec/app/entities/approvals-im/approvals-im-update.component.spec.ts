/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ApprovalsImUpdateComponent } from 'app/entities/approvals-im/approvals-im-update.component';
import { ApprovalsImService } from 'app/entities/approvals-im/approvals-im.service';
import { ApprovalsIm } from 'app/shared/model/approvals-im.model';

describe('Component Tests', () => {
    describe('ApprovalsIm Management Update Component', () => {
        let comp: ApprovalsImUpdateComponent;
        let fixture: ComponentFixture<ApprovalsImUpdateComponent>;
        let service: ApprovalsImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ApprovalsImUpdateComponent]
            })
                .overrideTemplate(ApprovalsImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ApprovalsImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApprovalsImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ApprovalsIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.approvals = entity;
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
                    const entity = new ApprovalsIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.approvals = entity;
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
