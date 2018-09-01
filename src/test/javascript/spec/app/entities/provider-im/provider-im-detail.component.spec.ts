/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProviderImDetailComponent } from 'app/entities/provider-im/provider-im-detail.component';
import { ProviderIm } from 'app/shared/model/provider-im.model';

describe('Component Tests', () => {
    describe('ProviderIm Management Detail Component', () => {
        let comp: ProviderImDetailComponent;
        let fixture: ComponentFixture<ProviderImDetailComponent>;
        const route = ({ data: of({ provider: new ProviderIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProviderImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProviderImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProviderImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.provider).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
