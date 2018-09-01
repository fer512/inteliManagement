/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { NotificationImUpdateComponent } from 'app/entities/notification-im/notification-im-update.component';
import { NotificationImService } from 'app/entities/notification-im/notification-im.service';
import { NotificationIm } from 'app/shared/model/notification-im.model';

describe('Component Tests', () => {
    describe('NotificationIm Management Update Component', () => {
        let comp: NotificationImUpdateComponent;
        let fixture: ComponentFixture<NotificationImUpdateComponent>;
        let service: NotificationImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [NotificationImUpdateComponent]
            })
                .overrideTemplate(NotificationImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NotificationImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotificationImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NotificationIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.notification = entity;
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
                    const entity = new NotificationIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.notification = entity;
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
