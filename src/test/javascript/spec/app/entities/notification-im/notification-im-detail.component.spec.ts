/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { NotificationImDetailComponent } from 'app/entities/notification-im/notification-im-detail.component';
import { NotificationIm } from 'app/shared/model/notification-im.model';

describe('Component Tests', () => {
    describe('NotificationIm Management Detail Component', () => {
        let comp: NotificationImDetailComponent;
        let fixture: ComponentFixture<NotificationImDetailComponent>;
        const route = ({ data: of({ notification: new NotificationIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [NotificationImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NotificationImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NotificationImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.notification).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
